public class MyTestingClass {
    private final int id;
    private final String code;

    public MyTestingClass(int id, String code) {
        this.id = id;
        this.code = code;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + id;
        for (int i = 0; i < code.length(); i++) {
            hash = 31 * hash + code.charAt(i);
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MyTestingClass)) {
            return false;
        }
        MyTestingClass other = (MyTestingClass) obj;
        return id == other.id && code.equals(other.code);
    }

    @Override
    public String toString() {
        return "MyTestingClass{id=" + id + ", code='" + code + "'}";
    }
}