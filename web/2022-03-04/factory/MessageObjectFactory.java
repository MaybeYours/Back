package xyz.itwill02.factory;

//Factory ������ ������ ����� Ŭ���� : Factory Ŭ���� �Ǵ� Provider Ŭ����
// => �ν��Ͻ��� �����Ͽ� ��ȯ�ϴ� ����� �����ϴ� Ŭ����
public class MessageObjectFactory {
	private static MessageObjectFactory _factory;
	
	private MessageObjectFactory() {
		// TODO Auto-generated constructor stub
	}
	
	static {
		_factory=new MessageObjectFactory();
	}
	
	public static MessageObjectFactory getFactory() {
		return _factory;
	}
	
	//Ŭ������ �����ϱ� ���� ���
	public static final int HELLO_MSG=1;
	public static final int HI_MSG=2;
	
	//�������� ���޹޾� ���Ͽ� �������̽��� ��ӹ��� �ڽ�Ŭ������ �ν��Ͻ��� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	public MessageObject getMessageObject(int messageObject) {
		MessageObject object=null;
		switch(messageObject) {
			case HELLO_MSG: 
				object=new HelloMessageObject();
				break;
			case HI_MSG:
				object=new HiMessageObject();
				break;
		}
		return object;
	}
}












