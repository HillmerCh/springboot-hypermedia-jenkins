package org.hillmerch.player.model.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Player {

	public enum Position {
		OUTFIELD,
		INFIELD,
		BATTERY
	}

	private @Id @GeneratedValue Long id;
	@NotBlank
	private String name;

	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Position position;

	@Min(1L)
	@NotNull
	private Long uniformNumber;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String team;

	public Player() {

	}

	public Player(String name, Position position, Long uniformNumber, String phoneNumber, String team) {
		this.name = name;
		this.position = position;
		this.uniformNumber = uniformNumber;
		this.phoneNumber = phoneNumber;
		this.team = team;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public Long getUniformNumber() {
		return uniformNumber;
	}

	public void setUniformNumber(Long uniformNumber) {
		this.uniformNumber = uniformNumber;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "Player{" +
				"id=" + id +
				", name='" + name + '\'' +
				", position=" + position +
				", uniformNumber=" + uniformNumber +
				", phoneNumber='" + phoneNumber + '\'' +
				", team='" + team + '\'' +
				'}';
	}


}
