package ch.bittailor.iot.mqttsn.messages;

public interface MessageVisitor {
	void visit(Connect connect);
	void visit(Connack connack);
	void visit(Register register);
	void visit(Regack regack);
	void visit(Publish publish);
}