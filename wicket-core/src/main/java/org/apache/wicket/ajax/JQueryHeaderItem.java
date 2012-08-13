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
package org.apache.wicket.ajax;

import org.apache.wicket.markup.head.HeaderItem;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.resource.JQueryResourceReference;
import org.apache.wicket.settings.IJavaScriptLibrarySettings;

/**
 * A utility class for retrieving the {@link HeaderItem} which contributes
 * the version of jQuery that is packaged with Wicket. This class should not be
 * called directly, but rather {@link IJavaScriptLibrarySettings#getJQueryHeaderItem() }
 * should be used to avoid version conflicts.
 * @author Jesse Long
 */
public class JQueryHeaderItem
{
	/**
	 * Private constructor for utility class.
	 */
	private JQueryHeaderItem(){}
	
	/**
	 * Returns a {@link JavaScriptHeaderItem} which contributes a reference to the
	 * version of jQuery that is packaged with Wicket. This method should not be
	 * called directly, but rather {@link IJavaScriptLibrarySettings#getJQueryHeaderItem() }
	 * should be used to avoid version conflicts.
	 * @return Returns a {@link JavaScriptHeaderItem} which contributes a reference to the
	 * version of jQuery that is packaged with Wicket
	 */
	public static JavaScriptHeaderItem get()
	{
	    return JavaScriptHeaderItem.forReference(JQueryResourceReference.get());
	}
}
