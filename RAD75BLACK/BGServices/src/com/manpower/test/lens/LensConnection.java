package com.manpower.test.lens;

import com.bgt.lens.JLens;
import com.bgt.lens.LensException;
import com.bgt.lens.LensSession;
import com.bgt.lens.ServerBusyException;

public class LensConnection {

	public static LensSession getSession(String lensHost, int lensPort) throws LensException, ServerBusyException
    {
        LensSession session=null;
        LensException lenesEX=null;
        ServerBusyException sbEx=null;
        
        try
        {
            session=JLens.createSession(lensHost,lensPort);
            session.setEnableTransactionTimeout(true);
            session.setTransactionTimeout(60000);
        }
        catch(LensException le)
        {
            le.printStackTrace();
            throw le;
        }
        
        for(int i=0, maxRetry=3;i<maxRetry;i++)
        {
	        try
	        {
	            session.open();
	            return session;
	        }
	        catch(LensException le)
	        {
	            le.printStackTrace();
	            lenesEX=le;
	        }
	        catch(ServerBusyException sbe)
	        {
	            sbe.printStackTrace();
	            sbEx=sbe;
	        }
        }
        
        if(lenesEX!=null)
        {
            throw lenesEX;
        }
        
        if(sbEx!=null)
        {
            throw sbEx;
        }
        
        return session;
    }
    
    public static void closeSession(LensSession session)
    {
        if(session!=null)
        {
            try
            {
                session.close();
            }
            catch(LensException le)
            {
                le.printStackTrace();
            }
        }
    }
	
}
