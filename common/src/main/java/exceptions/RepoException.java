package exceptions;

public class RepoException extends RuntimeException{
    public RepoException(String error){
        super(error);
    }
}
