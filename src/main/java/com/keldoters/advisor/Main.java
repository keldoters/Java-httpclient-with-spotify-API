package com.keldoters.advisor;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i+=2) {
            switch (args[i]) {
                case "-access" :
                    Server.accessServer = args[i+1];
                    break;
                case "-resource" :
                    Config.API_PATH = args[i+1];
                    break;
                case "-page" :
                    Config.PAGE = Integer.parseInt(args[i+1]);
                    break;
            }
        }
        UserInterface ui = new UserInterface();
        ui.start();
    }
}
