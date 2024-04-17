package org.example.exception;

public class URLisNotFind extends  Exception
{
    static final String msg = "Url is not find";

    public URLisNotFind() {
        super(msg);
    }

}
