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
package org.apache.wicket.protocol.ws.api;

import java.util.Collections;

import org.apache.wicket.Application;
import org.apache.wicket.ajax.WicketAjaxJQueryHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A header item that provides the JavaScript that may be used to create WebSocket
 * connections in the browser. The benefit over usage of plain &lt;code&gt;window.WebSocket&lt;code&gt;
 * is that it supports handling of Wicket's &lt;ajax-response&gt; responses.
 *
 * @since 6.0
 */
public class WicketWebSocketJQueryHeaderItem extends JavaScriptReferenceHeaderItem
{
	private static final long serialVersionUID = 1;

	private static final WicketWebSocketJQueryHeaderItem instance = new WicketWebSocketJQueryHeaderItem();

	/**
	 * @return the singleton instance
	 */
	public static WicketWebSocketJQueryHeaderItem get()
	{
		return instance;
	}

	private WicketWebSocketJQueryHeaderItem()
	{
		super(new JavaScriptResourceReference(WicketWebSocketJQueryHeaderItem.class, "res/js/wicket-websocket-jquery.js"));
	}

	@Override
	public Iterable<? extends HeaderItem> getDependencies()
	{
		final HeaderItem wicketAjaxHeaderItem;
		if (Application.exists()) {
			wicketAjaxHeaderItem = Application.get().getJavaScriptLibrarySettings().getWicketAjaxHeaderItem();
		}
		else {
			wicketAjaxHeaderItem = WicketAjaxJQueryHeaderItem.get();
		}
		return Collections.singletonList(wicketAjaxHeaderItem);
	}
}
