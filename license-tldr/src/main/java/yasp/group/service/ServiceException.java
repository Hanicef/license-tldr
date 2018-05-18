
package yasp.group.service;

public class ServiceException extends RuntimeException {
	private String table;
	private int id;

	public ServiceException() {
		super();
		this.table = "";
		this.id = 0;
	}

	public ServiceException(String message) {
		super(message);
		this.table = "";
		this.id = 0;
	}

	public ServiceException(String table, int id, String message) {
		super(message);
		this.table = table;
		this.id = id;
	}

	public String getTable() {
		return table;
	}

	public int getId() {
		return id;
	}

	@Override
	public String getMessage() {
		return table + ":" + id + ": " + super.getMessage();
	}
}
