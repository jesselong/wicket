package org.apache.wicket.examples.base;

import java.util.Arrays;

import org.apache.wicket.bootstrap.Bootstrap;
import org.apache.wicket.examples.base.prettify.Prettify;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

public class Examples extends JavaScriptReferenceHeaderItem {
	private static final long serialVersionUID = 1L;

	private static final Examples instance = new Examples();

	public static Examples get() {
		return instance;
	}

	public static void renderHead(IHeaderResponse response) {
		response.render(get());
	}

	private Examples() {
		super(new JavaScriptResourceReference(Examples.class, "examples.js"));
	}

	@Override
	public Iterable<? extends HeaderItem> getDependencies() {
		return Arrays.asList(Bootstrap.get(), Prettify.get());
	}
}
