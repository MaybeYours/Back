package xyz.itwill02.factory;

public class MessagePrint {
	public void messagePrint() {
		//MessageObject object=MessageObjectFactory.getFactory().getMessageObject(MessageObjectFactory.HELLO_MSG);
		MessageObject object=MessageObjectFactory.getFactory().getMessageObject(MessageObjectFactory.HI_MSG);
		
		//�������̽��� ���������� �̿��Ͽ� �޼ҵ带 ȣ���Ͽ� �ڽ� �ν��Ͻ��� �������̵� �޼ҵ� ȣ��
		// => �������̵忡 ���� ������
		String message=object.getMessage();
		System.out.println("message = "+message);
	}
}
