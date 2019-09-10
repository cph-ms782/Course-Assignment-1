
package dto;

import entities.GroupMember;


public class GroupMemberDTO {
    private String name;
    private String color;
    private int studentID;

    public GroupMemberDTO(GroupMember groupMember) {
        this.name = groupMember.getName();
        this.color = groupMember.getColor();
        this.studentID = Math.toIntExact(studentID);
    }
    
}
