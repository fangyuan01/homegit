package fangyuan;

public class Name implements Comparable {
	public String firstname;
	public String lastname;

	public Name(String firstname, String lastname) {
		// TODO Auto-generated constructor stub
		this.firstname = firstname;
		this.lastname = lastname;

	}

	public int compareTo(Object obj) {
		Name i = (Name) obj;
		int last = this.lastname.compareTo(i.lastname);
		return (last != 0 ? last : firstname.compareTo(i.firstname));

	}

	public String toString() {
		return firstname + " " + lastname;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Name) {
			Name n = (Name) obj;
			if ((this.firstname == n.firstname)
					&& (this.lastname == n.lastname))
				return true;
		}

		return false;

	}
}
