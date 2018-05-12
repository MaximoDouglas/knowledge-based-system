package br.ufal.ic.ia.skynet.motor_inferencia.model;

public class BackwardResponse {
	private boolean question = false;
	private boolean undefinited = true;
	private String object;
	
	public boolean isQuestion() {
		return question;
	}
	public void setQuestion(boolean question) {
		this.question = question;
	}
	public boolean isUndefinited() {
		return undefinited;
	}
	public void setUndefinited(boolean undefinited) {
		this.undefinited = undefinited;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
}
