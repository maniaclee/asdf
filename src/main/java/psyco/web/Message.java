package psyco.web;

public class Message {
	boolean success = true;
	String type;
	Object data;

	public Message() {
	}

	public Message(boolean success, String type, Object data) {
		super();
		this.success = success;
		this.type = type;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
