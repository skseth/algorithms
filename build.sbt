
lazy val commonSettings = Seq(
  organization := "com.skseth",
  version := "0.1.0",
  scalaSource in Test := baseDirectory.value / "tests",
  libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test",
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-oD"),

  scalaVersion := "2.11.4",
  scalaHome := Some(file("/usr/local/Cellar/scala/2.11.4/libexec"))
)

lazy val structs = project.in(file("structs")).
						settings(commonSettings: _*)

lazy val sorting = project.in(file("sorting")).
						dependsOn(structs % "compile->compile;test").
						settings(commonSettings: _*)

lazy val graphs = project.in(file("graphs")).
						dependsOn(structs % "compile->compile;test").
						settings(commonSettings: _*)

lazy val lookup = project.in(file("lookup")).
            dependsOn(structs % "compile->compile;test").
            settings(commonSettings: _*)


lazy val arithmetic = project.in(file("arithmetic")).
            settings(commonSettings: _*)


lazy val root = (project in file(".")).
  					aggregate(structs,sorting, graphs,lookup, arithmetic)
