package xyz.itwill02.factory;

public class MessagePrint {
	public void messagePrint() {
		//MessageObject object=MessageObjectFactory.getFactory().getMessageObject(MessageObjectFactory.HELLO_MSG);
		MessageObject object=MessageObjectFactory.getFactory().getMessageObject(MessageObjectFactory.HI_MSG);
		
		//인터페이스의 참조변수를 이용하여 메소드를 호출하여 자식 인스턴스의 오버라이드 메소드 호출
		// => 오버라이드에 의해 다형성
		String message=object.getMessage();
		System.out.println("message = "+message);
	}
}
