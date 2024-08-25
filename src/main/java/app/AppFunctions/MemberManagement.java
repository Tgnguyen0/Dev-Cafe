package app.AppFunctions;

import java.util.ArrayList;

import app.Object.Member;

public class MemberManagement {
    ArrayList<Member> ml;

    public MemberManagement() {
        ml = new ArrayList<Member>();
    }

    public ArrayList<Member> getAllMember() {
        return ml;
    }

    public boolean addMember(Member newMember) {
        return ml.add(newMember);
    }

    public boolean addAllMember(ArrayList<Member> newList) {
        return ml.addAll(newList);
    }

    public boolean deleteMember(String id) {
        for (int i = 0 ; i < ml.size() ; i++) {
            if (ml.get(i).getId().equals(id)) {
                ml.remove(i);
                return true;
            }
        }

        return false;
    }

    public Member searchMember(String phone) {
        for (int i = 0 ; i < ml.size() ; i++) {
            if (ml.get(i).getPhone().equals(phone)) {
                return ml.get(i);
            }
        }

        return null;
    }
}
