package business;

import entities.Member;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//DEFINE QUE METODOS EXISTEN EN MEMBER
public interface MemberService {

    /**
     * Retorna TRUE si se creo
     * Retorna FALSE si no se creo
     * @param member Miembro a crear
     * @return Retorna el valor de si se creo o no
     */
    Member insert(Member member);

    /**
     * Elimina un miembro
     * @param member
     */
    public void delete(Member member);

    /**
     * Busca un miembro de la DB segun su ID
     * @param key se le pasa la ID
     * @return Devuelve el miembro y todos sus datos
     */
    Member searchForId(Integer key);

    List<Member> obtainAll();

    LocalDate membershipEndDate(Member member, LocalDate parsedDate);

    void renewMembership(int memberId);

    void updateContactInfo(int memberId, String newContactInfo);

    public boolean isMemberShipActive(int memberId);

    LocalDate parsedDate(String parsingDate);
}
