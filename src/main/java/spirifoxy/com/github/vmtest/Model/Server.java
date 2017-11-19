package spirifoxy.com.github.vmtest.Model;

public class Server {
	private VM vm;
	
	private Server() {
		vm = new VM();
	}
	
	private static class ServerHolder {
		private final static Server instance = new Server();
	}
	
	public static Server getInstance() {
		return ServerHolder.instance;
	}
	
	public VM getVM() {
		return vm;
	}
	
}
