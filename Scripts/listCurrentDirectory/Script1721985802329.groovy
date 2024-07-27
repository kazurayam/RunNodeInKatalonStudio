import com.kazurayam.subprocessj.OSType
import com.kazurayam.subprocessj.Subprocess
import com.kazurayam.subprocessj.Subprocess.CompletedProcess

/*
 * サブプロセスをforkしてその中で任意のOSコマンドを実行できるJavaクラス Subporcessj の使い方を
 * デモンストレーションする。
 * カレントフォルダの内容のリストを取得する。
 * MacないしLinuxならば ls コマンド、Windowsならばcmd.exeに dirオプションを指定して実行する。
 * SubprocessjはOSコマンドがterminateするのを待つ。
 * OSコマンドがSTDOUTへ出力したテキストを読み取ってSystem.outにprintlnする。
 * OSコマンドがSTEDRRへ出力したテキストを読み取ってSystem.errにprintlnする。
 */
Subprocess.CompletedProcess cp;
if (OSType.isMac() || OSType.isUnix()) {
	cp = new Subprocess().cwd(new File("."))
			.run(Arrays.asList("sh", "-c", "ls"));
} else {
	cp = new Subprocess().cwd(new File("."))
			.run(Arrays.asList("cmd.exe", "/C", "dir"));
}
assert 0 == cp.returncode()
assert cp.stdout().size() > 0
assert cp.stdout().toString().contains("Test Cases");

cp.stderr().forEach({ line -> System.err.println line })

cp.stdout().forEach({ line -> System.out.println line })
/*
Checkpoints
Data Files
Drivers
Include
Keywords
Libs
Object Repository
Plugins
Profiles
Reports
RunNodeInKatalonStudio.prj
Scripts
Test Cases
Test Listeners
Test Suites
bin
build.gradle
console.properties
 */
