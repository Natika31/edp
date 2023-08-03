package com.projet.edp;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({"com.projet.edp.directoryViewerTest",
"com.projet.edp.fileViewerTest"})
public class AllTests {

}
