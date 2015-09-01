package org.effting.tools;

/**
 *
 * @author Charles Kafels Effting
 */
abstract class AbstractOperations implements Operations {

//    protected static boolean runCommand(String command, String args, String file) {
//        logger.debug("Trying to exec:\n   cmd = " + command + "\n   args = " + args + "\n   %s = " + file);
//
//        String[] parts = prepareCommand(command, args, file);
//        try {
//            Process p = Runtime.getRuntime().exec(parts);
//            if (p == null) {
//                return false;
//            }
//
//            try {
//                int retval = p.exitValue();
//                if (retval == 0) {
//                    logger.warn("Process ended immediately.");
//                    return false;
//                } else {
//                    logger.warn("Process crashed.");
//                    return false;
//                }
//            } catch (IllegalThreadStateException itse) {
//                logger.info("Process is running.");
//                return true;
//            }
//        } catch (IOException e) {
//            logger.warn("Error running command.", e);
//            return false;
//        }
//    }
//
//    protected static String[] prepareCommand(String command, String args, String file) {
//        List<String> parts = new ArrayList<>();
//        parts.add(command);
//
//        if (args != null) {
//            for (String s : args.split(" ")) {
//                s = String.format(s, file); // put in the filename thing
//
//                parts.add(s.trim());
//            }
//        }
//
//        return parts.toArray(new String[parts.size()]);
//    }
}
