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
package org.apache.wicket.settings.def;

import org.apache.wicket.ajax.JQueryHeaderItem;
import org.apache.wicket.ajax.WicketAjaxDebugJQueryHeaderItem;
import org.apache.wicket.ajax.WicketAjaxJQueryHeaderItem;
import org.apache.wicket.ajax.WicketEventJQueryHeaderItem;
import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.settings.IJavaScriptLibrarySettings;
import org.apache.wicket.util.lang.Args;

/**
 * @since 6.0
 */
public class JavaScriptLibrarySettings implements IJavaScriptLibrarySettings
{
	private HeaderItem jQueryHeaderItem = JQueryHeaderItem.get();

	private HeaderItem wicketEventHeaderItem = WicketEventJQueryHeaderItem.get();

	private HeaderItem wicketAjaxHeaderItem = WicketAjaxJQueryHeaderItem.get();

	private HeaderItem wicketAjaxDebugHeaderItem = WicketAjaxDebugJQueryHeaderItem.get();

	@Override
	public HeaderItem getJQueryHeaderItem()
	{
		return jQueryHeaderItem;
	}

	@Override
	public void setJQueryHeaderItem(HeaderItem jQueryHeaderItem)
	{
		this.jQueryHeaderItem = Args.notNull(jQueryHeaderItem, "jQueryHeaderItem");
	}

	@Override
	public HeaderItem getWicketEventHeaderItem()
	{
		return wicketEventHeaderItem;
	}

	@Override
	public void setWicketEventHeaderItem(HeaderItem wicketEventHeaderItem)
	{
		this.wicketEventHeaderItem = Args.notNull(wicketEventHeaderItem, "wicketEventHeaderItem");
	}

	@Override
	public HeaderItem getWicketAjaxHeaderItem()
	{
		return wicketAjaxHeaderItem;
	}

	@Override
	public void setWicketAjaxHeaderItem(HeaderItem wicketAjaxHeaderItem)
	{
		this.wicketAjaxHeaderItem = Args.notNull(wicketAjaxHeaderItem, "wicketAjaxHeaderItem");
	}

	@Override
	public HeaderItem getWicketAjaxDebugHeaderItem()
	{
		return wicketAjaxDebugHeaderItem;
	}

	@Override
	public void setWicketAjaxDebugHeaderItem(HeaderItem wicketAjaxDebugHeaderItem)
	{
		this.wicketAjaxDebugHeaderItem = Args.notNull(wicketAjaxDebugHeaderItem,
			"wicketAjaxDebugHeaderItem");
	}

}
