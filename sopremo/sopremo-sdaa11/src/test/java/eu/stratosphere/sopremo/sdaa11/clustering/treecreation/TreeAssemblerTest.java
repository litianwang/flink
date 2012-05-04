/***********************************************************************************************************************
 *
 * Copyright (C) 2010 by the Stratosphere project (http://stratosphere.eu)
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 **********************************************************************************************************************/
package eu.stratosphere.sopremo.sdaa11.clustering.treecreation;

import java.util.Arrays;

import org.junit.Test;

import eu.stratosphere.sopremo.sdaa11.clustering.Point;
import eu.stratosphere.sopremo.testing.SopremoTestPlan;
import eu.stratosphere.sopremo.type.IJsonNode;
import eu.stratosphere.sopremo.type.ObjectNode;
import eu.stratosphere.sopremo.type.TextNode;

/**
 * @author skruse
 * 
 */
public class TreeAssemblerTest {

	@Test
	public void testPreparation() {

		final TreeAssembler assembler = new TreeAssembler();

		final SopremoTestPlan plan = new SopremoTestPlan(assembler);

		final IJsonNode point1 = new Point("p1", Arrays.asList("1", "2", "3"))
				.write((IJsonNode) null);
		final IJsonNode point2 = new Point("p3", Arrays.asList("1", "2", "3"))
				.write((IJsonNode) null);

		final ObjectNode cluster1 = new ObjectNode();
		cluster1.put(TreeAssembler.DUMMY_KEY, TreeAssembler.DUMMY_NODE);
		cluster1.put("id", new TextNode("c1"));
		cluster1.put("clustroid", point1);

		final ObjectNode cluster2 = new ObjectNode();
		cluster2.put(TreeAssembler.DUMMY_KEY, TreeAssembler.DUMMY_NODE);
		cluster2.put("id", new TextNode("c2"));
		cluster2.put("clustroid", point2);

		plan.getInput(0).add(cluster1).add(cluster2);

		plan.run();

		for (final IJsonNode node : plan.getActualOutput(0)) {
			System.out.println(node);
//			final Object tree = SopremoUtil
//					.stringToObject(((TextNode) ((ObjectNode) node).get("tree"))
//							.getJavaValue());
//			System.out.println(tree);
		}

	}

}
