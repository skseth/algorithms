
lazy val commonSettings = Seq(
  organization := "com.skseth",
  version := "0.1.0",
  scalaSource in Test := baseDirectory.value / "tests",
  libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test",
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD")
)

lazy val structs = project.in(file("structs")).
						settings(commonSettings: _*)

lazy val sorting = project.in(file("sorting")).
						dependsOn(structs % "compile->compile;test").
						settings(commonSettings: _*)

lazy val graphs = project.in(file("graphs")).
						dependsOn(structs % "compile->compile;test").
						settings(commonSettings: _*)

lazy val root = (project in file(".")).
  					aggregate(structs,sorting)
