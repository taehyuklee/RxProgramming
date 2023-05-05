# Postgresql with r2dbc

&nbsp; When just inserting, unless the PK Column is set to Serial, the PK is not generated automatically (auto-increment), and if I arbitrarily create and insert it into the Id value, it becomes an update, not an insert. Accordingly, the following implementation of Persistable should be applied to the entity, and isNew should be set to true when inserting and false when updating.

```java
@Data
@Table("Person")
@Accessors(chain = true)
public class Person implements Persistable<String> {

    @Transient
    private boolean isNew = true;

    @Id
    private String id;

    private String email;

    private String name;

    private String phoneNum;

    private Integer score;


    @Override
    public boolean isNew() {
        return isNew;
    }
}

```