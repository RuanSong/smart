package org.framework.smart.thread;

/**
 * @author rosan
 * @date: 2017/10/16 下午10:43
 * @version:1.0
 */
public class ClientThread extends Thread {
    private Sequence sequence;
    public ClientThread(Sequence sequence){
        this.sequence = sequence;
    }

    @Override
    public void run() {
        for(int i = 0 ; i < 3;i++){
            System.out.println(Thread.currentThread().getName()+"=>"+sequence.getNumber());
        }
    }
}
