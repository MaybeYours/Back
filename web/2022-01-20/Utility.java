package xyz.itwill.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.regex.Pattern;

//�����α׷� �ۼ��� �ʿ��� ����� �����ϱ� ���� Ŭ����
public class Utility {
	//���ڿ��� ���޹޾� ��ȣȭ ó���Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static String encrypt(String source) {
		//��ȣȭ�� ���ڿ��� �����ϱ� ���� ���� ����
		String password="";
		try {
			//MessageDigest : ��ȣȭ ó�� ����� �����ϴ� Ŭ����
			//MessageDigest.getInstance(String algorithm) : ��ȣȭ ó�� �˰����� ������
			//MessageDigest �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
			// => �Ű������� �߸��� ��ȣȭ �˰����� ���޵� ��� NoSuchAlgorithmException �߻�
			//��ȣȭ �˰���(�ܹ���) : MD5, SHA-1, SHA-256(����), SHA-512 ��
			//��ȣȭ �˰���(�ֹ���) : AES-123, RSA ��
			MessageDigest md=MessageDigest.getInstance("SHA-256");
			
			//MessageDigest.update(byte[] input) : MessageDigest �ν��Ͻ��� ��ȣȭ ó���ϱ� ����
			//���ڿ��� byte �迭�� ���޹޾� �����ϴ� �޼ҵ�
			//String.getBytes() : String �ν��Ͻ��� ����� ���ڿ��� byte �迭�� ��ȯ�ϴ� �޼ҵ�
			md.update(source.getBytes());
			
			//MessageDigest.digest() : MessageDigest �ν��Ͻ��� ����� ������ �̿��Ͽ� ��ȣȭ  
			//ó���Ͽ� byte �迭�� ��ȯ�ϴ� �޼ҵ� 
			byte[] digest=md.digest();
			
			//��ȣȭ ó���Ǿ� ��ȯ�� byte �迭�� ��Ұ��� 16���� ������ ���ڿ��� ��ȯ�Ͽ� ������ �߰��Ͽ� ����
			for(int i=0;i<digest.length;i++) {
				password+=Integer.toHexString(digest[i]&0xff);
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("[����]�߸��� ��ȣȭ �˰����� ��� �Ͽ����ϴ�.");
		}
		return password;
	}
	
	//���ڿ��� ���޹޾� �±� ���� ���ڿ��� ��� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static String stripTag(String source) {
		//Pattern Ŭ���� : ����ǥ������ �����ϱ� ���� Ŭ����
		//Pattern.compile(String regEx) : ����ǥ������ ����� Pattern �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
		//Pattern.CASE_INSENSITIVE : ����ǥ���Ŀ��� ��ҹ��ڸ� �������� �ʵ��� �����ϴ� ���
		Pattern htmlTag=Pattern.compile("\\<.*?\\>",Pattern.CASE_INSENSITIVE);
		
		//Pattern.matcher(String source) : ����ǥ���İ� �񱳰��� ����� Matcher �ν��Ͻ��� ��ȯ�ϴ� �޼ҵ�
		//Matcher.replaceAll(String replacement) : Matcher �ν��Ͻ��� ����� �񱳰��� ����ǥ������
		//���ڿ��� ��� ã�� ��ȯ�Ͽ� ��ȯ�ϴ� �޼ҵ�
		source=htmlTag.matcher(source).replaceAll("");//���ڿ����� �±׸� �����Ͽ� ��ȯ
		
		return source;
	}
	
	//���ڿ��� ���޹޾� �±� ���� ��ȣ�� ȸ�ǹ��ڷ� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static String escapeTag(String source) {
		return source.replace("<", "&lt;").replace(">", "&gt;");
	}
	
	//�ӽú�й�ȣ�� �����Ͽ� ��ȯ�ϴ� �޼ҵ�
	public static String newPassword() {
		//Random : ������ ���� ����� �����ϴ� Ŭ����
		Random random=new Random();
		
		//��й�ȣ�� ���� ���ڸ� ������ ���ڿ� ����
		String str="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		String password="";
		for(int i=1;i<=8;i++) {
			//Random.nextInt(int bound) : 0~bound-1 ������ ���� �������� ��ȯ�ϴ� �޼ҵ�
			password+=str.charAt(random.nextInt(str.length()));
		}
		
		return password;
	}
	
}













