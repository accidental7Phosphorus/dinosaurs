public enum Species {
    TYRANNOSAURUS, TRICERATOPS, PTERODACTYL, CAT;

    public String toCapString(){
        String lowercase = this.toString().toLowerCase();
        return Character.toUpperCase(lowercase.charAt(0)) + lowercase.substring(1);
    }
}
