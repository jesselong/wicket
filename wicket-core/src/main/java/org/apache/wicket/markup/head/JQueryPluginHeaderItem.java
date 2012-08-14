/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket.markup.head;

import java.util.Arrays;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.JQueryHeaderItem;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.request.resource.ResourceReference;

/**
 * Header item for use when including jQuery plugins. It already contains a reference to jQuery.
 * 
 * @author papegaaij
 */
public class JQueryPluginHeaderItem extends JavaScriptReferenceHeaderItem
{
	private static final long serialVersionUID = 1L;

	public JQueryPluginHeaderItem(ResourceReference reference)
	{
		super(reference);
	}

	public JQueryPluginHeaderItem(ResourceReference reference, PageParameters pageParameters, String id, boolean defer, String charset, String condition)
	{
		super(reference, pageParameters, id, defer, charset, condition);
	}
	
	/**
	 * Returns a new header item for the plugin referenced by {@code reference}.
	 * @param reference
	 *	    the resource reference for the plugin
	 * @return a new header item referencing jQuery and the plugin referenced by {@code reference}.
	 */
	public static JQueryPluginHeaderItem forReference(ResourceReference reference)
	{
	    return new JQueryPluginHeaderItem(reference);
	}

	@Override
	public Iterable<? extends HeaderItem> getDependencies()
	{
		final HeaderItem backingLibraryHeaderItem;
		if (Application.exists())
		{
			backingLibraryHeaderItem = Application.get()
				.getJavaScriptLibrarySettings()
				.getJQueryHeaderItem();
		}
		else
		{
			backingLibraryHeaderItem = JQueryHeaderItem.get();
		}
		return Arrays.asList(backingLibraryHeaderItem);
	}
}
