package br.com.fas.usersregistry.entities;

import java.util.InputMismatchException;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected String id;

	protected String name;

	protected String cpf;
	
	@ManyToOne(fetch = FetchType.LAZY)
	protected Set<Address> addresses;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setNome(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		if (cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
				|| cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
				|| cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
				|| cpf.equals("99999999999") || (cpf.length() != 11))
			throw new IllegalArgumentException("Invalid CPF!");

		char dig10, dig11;
		int sm, i, r, num, weight;

		try {
			sm = 0;
			weight = 10;
			for (i = 0; i < 9; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * weight);
				weight = weight - 1;
			}

			r = 11 - (sm % 11);
			dig10 = r == 10 || r == 11 ? '0' : (char) (r + 48);

			sm = 0;
			weight = 11;
			for (i = 0; i < 10; i++) {
				num = (int) (cpf.charAt(i) - 48);
				sm = sm + (num * weight);
				weight = weight - 1;
			}

			r = 11 - (sm % 11);
			dig11 = r == 10 || r == 11 ? '0' : (char) (r + 48);

			if (dig10 != cpf.charAt(9) || dig11 != cpf.charAt(10))
				throw new IllegalArgumentException("Invalid CPF!");

		} catch (InputMismatchException err) {
			throw new IllegalArgumentException("Invalid CPF!", err);
		}

		this.cpf = cpf;
	}

}
