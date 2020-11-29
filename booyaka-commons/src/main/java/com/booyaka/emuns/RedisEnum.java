package com.booyaka.emuns;

public enum RedisEnum {

	SHIRO(0), SMS(1);

	private int index;

	RedisEnum(int index) {
		this.setIndex(index);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
