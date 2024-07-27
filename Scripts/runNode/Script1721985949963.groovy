import java.nio.file.Path
import java.nio.file.Paths

import com.kms.katalon.core.configuration.RunConfiguration
import com.kazurayam.subprocessj.OSType
import com.kazurayam.subprocessj.Subprocess
import com.kazurayam.subprocessj.CommandLocator
import com.kazurayam.subprocessj.CommandLocator.CommandLocatingResult;

import internal.GlobalVariable

/*
 * サブプロセスをforkしてその中で任意のOSコマンドを実行できるJavaクラス Subporcessj の使い方をデモする。
 * hello.jsスクリプトを指定してnodeを実行する。
 * hello.jsスクリプトは"Hello, world”と出力する。
 * Subprocessjはサブプロセスがterminateするのを待つ。
 * サブプロセスはSTDOUTへテキストを出力する。それを読み取ってSystem.outにprintlnする。
 * サブプロセスはSTEDRRへテキストを出力するかもしれない。それを読み取ってSystem.errにprintlnする。
 */
//CommandLocator.CommandLocatingResult cfr = CommandLocator.find("node");
//String nodeCommandPath = cfr.command()
String nodeCommandPath = GlobalVariable.NODE_COMMAND_PATH
println nodeCommandPath.toString()

Path projectDir = Paths.get(RunConfiguration.getProjectDir())
Path jsFile = projectDir.resolve('src/test/js/hello.js')

Subprocess.CompletedProcess cp;
cp = new Subprocess().cwd(new File("."))
			.run(Arrays.asList(nodeCommandPath, jsFile.toString()));

assert 0 == cp.returncode()
assert cp.stdout().size() > 0
assert cp.stdout().toString().contains("Hello, world");

cp.stderr().forEach({ line -> System.err.println line })

cp.stdout().forEach({ line -> System.out.println line })
