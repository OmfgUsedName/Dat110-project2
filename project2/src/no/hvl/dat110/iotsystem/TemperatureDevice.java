package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.broker.*;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		TemperatureSensor sn = new TemperatureSensor();
		final String topic = "temperature";

		// TODO - start

		Client client = new Client("sensor", Common.BROKERHOST, Common.BROKERPORT);
		client.connect();
		
		for (int i = 0; i < COUNT; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			client.publish(topic, "temperature: "+sn.read());
		}
		
		client.disconnect();

		System.out.println("Temperature device stopping ... ");

	}
}
