package com.ivanpozharskyi.kickstarter.datastorage;

public class Quote {
	private String name;

	public Quote(String name) {
		this.name = name;
	}

	@Override
	public String toString() {

		return name;
	}

}