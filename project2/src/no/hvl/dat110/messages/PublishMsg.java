package no.hvl.dat110.messages;

public class PublishMsg extends Message {
	
	// TODO: 
	// Implement objectvariables, constructor, get/set-methods, and toString method
	
	private String topic;
	private String msg;
	
	public PublishMsg(String user, String topic, String msg) {
		super(MessageType.PUBLISH,user);
		this.topic = topic;
		this.msg = msg;
	}

	public String getTopic() {
		return topic;
	}
	public String getMessage() {
		return msg;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	public void setMessage(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "Message [user: "+getUser()+" topic: "+topic+"]";
	};
}
