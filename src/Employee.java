public class Employee {
    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Para poder comprobar los datos
     *
     * @return informacion del empleado
     */
    @Override
    public String toString() {
        StringBuilder info;
        info = new StringBuilder(firstName +" "+  lastName + " es un empleado.");
        return info.toString();
    }
}
