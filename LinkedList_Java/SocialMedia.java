import java.util.*;

class FriendNode {
    String friendId;
    FriendNode next;

    FriendNode(String friendId) {
        this.friendId = friendId;
    }
}

class UserNode {
    String userId, name;
    int age;
    FriendNode friendsHead;
    UserNode next;

    UserNode(String userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
    }

    void addFriend(String friendId) {
        FriendNode node = new FriendNode(friendId);
        node.next = friendsHead;
        friendsHead = node;
    }

    boolean removeFriend(String friendId) {
        if (friendsHead == null) return false;
        if (friendsHead.friendId.equals(friendId)) { friendsHead = friendsHead.next; return true; }
        FriendNode curr = friendsHead;
        while (curr.next != null) {
            if (curr.next.friendId.equals(friendId)) { curr.next = curr.next.next; return true; }
            curr = curr.next;
        }
        return false;
    }

    Set<String> getFriendIds() {
        Set<String> ids = new HashSet<>();
        FriendNode curr = friendsHead;
        while (curr != null) { ids.add(curr.friendId); curr = curr.next; }
        return ids;
    }

    int friendCount() {
        int count = 0;
        FriendNode curr = friendsHead;
        while (curr != null) { count++; curr = curr.next; }
        return count;
    }
}

class SocialMediaSystem {
    UserNode head;

    UserNode findUser(String userId) {
        UserNode curr = head;
        while (curr != null) { if (curr.userId.equals(userId)) return curr; curr = curr.next; }
        return null;
    }

    void addUser(String userId, String name, int age) {
        if (findUser(userId) != null) { System.out.println("User ID " + userId + " already exists."); return; }
        UserNode node = new UserNode(userId, name, age);
        node.next = head;
        head = node;
        System.out.println("User '" + name + "' (ID: " + userId + ") added.");
    }

    void addFriendConnection(String uid1, String uid2) {
        UserNode u1 = findUser(uid1), u2 = findUser(uid2);
        if (u1 == null) { System.out.println("User " + uid1 + " not found."); return; }
        if (u2 == null) { System.out.println("User " + uid2 + " not found."); return; }
        if (u1.getFriendIds().contains(uid2)) { System.out.println("Users already friends."); return; }
        u1.addFriend(uid2);
        u2.addFriend(uid1);
        System.out.println("Friend connection added: " + u1.name + " <-> " + u2.name);
    }

    void removeFriendConnection(String uid1, String uid2) {
        UserNode u1 = findUser(uid1), u2 = findUser(uid2);
        if (u1 == null || u2 == null) { System.out.println("One or both users not found."); return; }
        boolean r1 = u1.removeFriend(uid2), r2 = u2.removeFriend(uid1);
        if (r1 && r2) System.out.println("Friend connection removed: " + u1.name + " <-> " + u2.name);
        else System.out.println("Users were not friends.");
    }

    void mutualFriends(String uid1, String uid2) {
        UserNode u1 = findUser(uid1), u2 = findUser(uid2);
        if (u1 == null || u2 == null) { System.out.println("One or both users not found."); return; }
        Set<String> mutual = new HashSet<>(u1.getFriendIds());
        mutual.retainAll(u2.getFriendIds());
        if (mutual.isEmpty()) { System.out.println("No mutual friends between " + u1.name + " and " + u2.name + "."); return; }
        System.out.println("\nMutual friends between " + u1.name + " and " + u2.name + ":");
        for (String fid : mutual) {
            UserNode friend = findUser(fid);
            System.out.println("  - " + (friend != null ? friend.name : "Unknown") + " (ID: " + fid + ")");
        }
    }

    void displayFriends(String userId) {
        UserNode user = findUser(userId);
        if (user == null) { System.out.println("User " + userId + " not found."); return; }
        System.out.println("\nFriends of " + user.name + " (ID: " + userId + "):");
        FriendNode curr = user.friendsHead;
        if (curr == null) { System.out.println("  No friends yet."); return; }
        while (curr != null) {
            UserNode friend = findUser(curr.friendId);
            System.out.println("  - " + (friend != null ? friend.name : "Unknown") + " (ID: " + curr.friendId + ")");
            curr = curr.next;
        }
    }

    void searchUser(String key) {
        boolean found = false;
        UserNode curr = head;
        while (curr != null) {
            if (curr.userId.equals(key) || curr.name.equalsIgnoreCase(key)) {
                System.out.println("Found -> ID: " + curr.userId + " | Name: " + curr.name + " | Age: " + curr.age + " | Friends: " + curr.friendCount());
                found = true;
            }
            curr = curr.next;
        }
        if (!found) System.out.println("No user found for '" + key + "'.");
    }

    void displayFriendCounts() {
        if (head == null) { System.out.println("No users in system."); return; }
        System.out.println("\n--- Friend Count per User ---");
        UserNode curr = head;
        while (curr != null) {
            System.out.println("  " + curr.name + " (ID: " + curr.userId + "): " + curr.friendCount() + " friends");
            curr = curr.next;
        }
        System.out.println();
    }
}

public class SocialMedia {
    public static void main(String[] args) {
        SocialMediaSystem social = new SocialMediaSystem();

        social.addUser("U001", "Alice", 22);
        social.addUser("U002", "Bob", 25);
        social.addUser("U003", "Charlie", 21);
        social.addUser("U004", "Diana", 23);
        social.addUser("U005", "Eve", 20);

        social.addFriendConnection("U001", "U002");
        social.addFriendConnection("U001", "U003");
        social.addFriendConnection("U002", "U003");
        social.addFriendConnection("U003", "U004");
        social.addFriendConnection("U004", "U005");
        social.addFriendConnection("U001", "U004");

        social.displayFriends("U001");
        social.displayFriends("U003");

        social.mutualFriends("U001", "U003");
        social.mutualFriends("U001", "U005");

        social.searchUser("Alice");
        social.searchUser("U004");

        social.displayFriendCounts();

        social.removeFriendConnection("U001", "U002");
        social.displayFriends("U001");
        social.displayFriendCounts();
    }
}
