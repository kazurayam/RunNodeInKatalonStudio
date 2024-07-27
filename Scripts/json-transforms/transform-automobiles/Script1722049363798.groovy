import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

import com.kazurayam.subprocessj.Subprocess
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path inputJson = projectDir.resolve("src/test/fixture/automobiles.json")
Path transformer = projectDir.resolve("src/test/js/transform-automobiles.js")
Path output = projectDir.resolve("build/tmp/testOutput/transformed.json")
Files.createDirectories(output.getParent())

String nodeCommandPath = GlobalVariable.NODE_COMMAND_PATH

Subprocess.CompletedProcess cp;
cp = new Subprocess().cwd(new File("."))
	.run(Arrays.asList(
		nodeCommandPath,           // ".../node"
		transformer.toString(),    // ".../transform-automobiles.js"
		inputJson.toString(),      // ".../automobiles.json"
		output.toString()          // ".../transformed.json"
		))

cp.stderr().forEach({ line ->
	System.err.println(line)
})
cp.stdout().forEach({ line ->
	System.out.println(line)
}) 
assert 0 == cp.returncode()
