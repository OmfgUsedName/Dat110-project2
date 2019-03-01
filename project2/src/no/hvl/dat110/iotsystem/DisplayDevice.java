package no.hvl.dat110.iotsystem;

import no.hvl.dat110.broker.Broker;
import no.hvl.dat110.broker.Dispatcher;
import no.hvl.dat110.broker.Storage;
import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		final String topic = "temperature";
		
		System.out.println("Display starting ...");

		Client client = new Client("display", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		client.createTopic(topic);
		client.subscribe(topic);

		PublishMsg msg = null;
		
		for (int i = 0; i < COUNT; i++) {
			msg = (PublishMsg)client.receive();
			System.out.println(msg.getMessage());
		}
		
		client.disconnect();
		
		System.out.println("Display stopping ... ");
		
		
	}
}
