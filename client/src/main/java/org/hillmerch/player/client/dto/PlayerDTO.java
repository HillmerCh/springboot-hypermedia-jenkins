package org.hillmerch.player.client.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import org.springframework.hateoas.RepresentationModel;


public class PlayerDTO extends RepresentationModel<PlayerDTO> {

	private Long id;
	@NotBlank
	private String name;
	@NotBlank(message = "Position must not be blank")
	private String position;
	@NotNull
	@Min(1L)
	private Long uniformNumber;

	//@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	private String phoneNumber;

	@NotBlank
	private String team;


	public PlayerDTO() {
	}

	public PlayerDTO(String name, String position, Long uniformNumber, String phoneNumber, String team) {
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
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
		return "PlayerDTO{" +
				"id=" + id +
				", name='" + name + '\'' +
				", position='" + position + '\'' +
				", uniformNumber=" + uniformNumber +
				", phoneNumber='" + phoneNumber + '\'' +
				'}';
	}

	public PlayerDTO name(String name){
		this.name = name; return this;
	}

	public PlayerDTO position(String position){
		this.position = position; return this;
	}

	public PlayerDTO uniformNumber(Long uniformNumber){
		this.uniformNumber = uniformNumber; return this;
	}

	public PlayerDTO phoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber; return this;
	}

	public PlayerDTO team(String team){
		this.team = team; return this;
	}

}
