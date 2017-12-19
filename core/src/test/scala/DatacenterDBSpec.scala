//: ----------------------------------------------------------------------------
//: Copyright (C) 2017 Verizon.  All Rights Reserved.
//:
//:   Licensed under the Apache License, Version 2.0 (the "License");
//:   you may not use this file except in compliance with the License.
//:   You may obtain a copy of the License at
//:
//:       http://www.apache.org/licenses/LICENSE-2.0
//:
//:   Unless required by applicable law or agreed to in writing, software
//:   distributed under the License is distributed on an "AS IS" BASIS,
//:   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//:   See the License for the specific language governing permissions and
//:   limitations under the License.
//:
//: ----------------------------------------------------------------------------
package nelson

import doobie.imports._
import scalaz._,Scalaz._
import storage.StoreOp

class DatacenterDBSpec extends NelsonSuite {

  "storage" should "be able to create datacenters then find it" in {
    nelson.storage.run(config.storage,
      StoreOp.createDatacenter(datacenter(testName))
      >> StoreOp.listDatacenters
    ).run should contain (testName)
  }

  "storage" should "be able to create namespaces" in {
    nelson.storage.run(config.storage,
      StoreOp.createNamespace(testName, NamespaceName("namespace")) >> StoreOp.listNamespacesForDatacenter(testName)
    ).run.map(_.name) should contain (NamespaceName("namespace"))
  }
}
