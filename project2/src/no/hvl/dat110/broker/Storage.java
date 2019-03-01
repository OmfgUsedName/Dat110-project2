package no.hvl.dat110.broker;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import no.hvl.dat110.common.Logger;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messagetransport.Connection;

public class Storage {

	protected ConcurrentHashMap<String, Set<String>> subscriptions;
	protected ConcurrentHashMap<String, ClientSession> clients;
	protected ConcurrentHashMap<String, Boolean> connected;
	protected ConcurrentHashMap<String, ArrayList<Message>> buffer;

	public Storage() {
		subscriptions = new ConcurrentHashMap<String, Set<String>>();
		clients = new ConcurrentHashMap<String, ClientSession>();
		connected =  new ConcurrentHashMap<>(); 
		buffer =  new ConcurrentHashMap<>(); 
	}

	public Collection<ClientSession> getSessions() {
		return clients.values();
	}

	public Set<String> getTopics() {

		return subscriptions.keySet();

	}

	public ClientSession getSession(String user) {

		ClientSession session = clients.get(user);

		return session;
	}

	public Set<String> getSubscribers(String topic) {

		return (subscriptions.get(topic));

	}

	public void addClientSession(String user, Connection connection) {
		reconnectUser(user, connection);
		buffer.put(user, new ArrayList<>());
	}

	public void removeClientSession(String user) {
		clients.remove(user);
	}

	public void createTopic(String topic) {
		Set<String> set = ConcurrentHashMap.newKeySet();
		subscriptions.put(topic, set);
	
	}

	public void deleteTopic(String topic) {
		subscriptions.remove(topic);
	}

	public void addSubscriber(String user, String topic) {
		subscriptions.get(topic).add(user);
		
	}

	public void removeSubscriber(String user, String topic) {
		subscriptions.get(topic).remove(user);
	}
	
	public void disconnectUser(String user) {
		clients.get(user).disconnect();
		connected.put(user, false);
	}
	
	public void reconnectUser(String user, Connection connection) {
		clients.put(user, new ClientSession(user,connection));
		connected.put(user, true);
		
	}
	public boolean isConnected(String user) {
		return connected.get(user);
	}
	public void addBufferForUser(String user, Message msg) {
		buffer.get(user).add(msg);
	}
	public ArrayList<Message> getBufferForUser(String user){
		return buffer.get(user);
	}
	public void removeBufferForUser(String user) {
		buffer.get(user).clear();
	}
}
