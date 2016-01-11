package edu.wpi.first.wpilibj.networktables;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

public class NetworkTablesJNI {
  static boolean libraryLoaded = false;
  static File jniLibrary = null;
  static {
    if (!libraryLoaded) {
      try {
        String osname = System.getProperty("os.name");
        String resname;
        if (osname.startsWith("Windows"))
          resname = "/Windows/" + System.getProperty("os.arch") + "/";
        else
          resname = "/" + osname + "/" + System.getProperty("os.arch") + "/";
        System.out.println("platform: " + resname);
        if (osname.startsWith("Windows"))
          resname += "ntcore.dll";
        else if (osname.startsWith("Mac"))
          resname += "libntcore.dylib";
        else
          resname += "libntcore.so";
        InputStream is = NetworkTablesJNI.class.getResourceAsStream(resname);
        if (is != null) {
          // create temporary file
          if (System.getProperty("os.name").startsWith("Windows"))
            jniLibrary = File.createTempFile("NetworkTablesJNI", ".dll");
          else if (System.getProperty("os.name").startsWith("Mac"))
            jniLibrary = File.createTempFile("libNetworkTablesJNI", ".dylib");
          else
            jniLibrary = File.createTempFile("libNetworkTablesJNI", ".so");
          // flag for delete on exit
          jniLibrary.deleteOnExit();
          OutputStream os = new FileOutputStream(jniLibrary);

          byte[] buffer = new byte[1024];
          int readBytes;
          try {
            while ((readBytes = is.read(buffer)) != -1) {
              os.write(buffer, 0, readBytes);
            }
          } finally {
            os.close();
            is.close();
          }

          System.load(jniLibrary.getAbsolutePath());
        } else {
                    // System.loadLibrary("ntcore");
        }
      } catch (IOException ex) {
        ex.printStackTrace();
        System.exit(1);
      }
      libraryLoaded = true;
    }
  }

  public static boolean containsKey(String key)
  {
    return false;
  }
  public static int getType(String key)
  {
    return 0;
  }

  public static boolean putBoolean(String key, boolean value)
  {
    return false;
  }
  public static boolean putDouble(String key, double value)
  {
    return false;
  }
  public static boolean putString(String key, String value)
  {
    return false;
  }
  public static boolean putRaw(String key, byte[] value)
  {
    return false;
  }
  public static boolean putRaw(String key, ByteBuffer value, int len)
  {
    return false;
  }
  public static boolean putBooleanArray(String key, boolean[] value)
  {
    return false;
  }
  public static boolean putDoubleArray(String key, double[] value)
  {
    return false;
  }
  public static boolean putStringArray(String key, String[] value)
  {
    return false;
  }

  public static void forcePutBoolean(String key, boolean value)
  {

  }
  public static void forcePutDouble(String key, double value)
  {

  }
  public static void forcePutString(String key, String value)
  {

  }
  public static void forcePutRaw(String key, byte[] value)
  {

  }
  public static void forcePutRaw(String key, ByteBuffer value, int len)
  {

  }
  public static void forcePutBooleanArray(String key, boolean[] value)
  {

  }
  public static void forcePutDoubleArray(String key, double[] value)
  {

  }
  public static void forcePutStringArray(String key, String[] value)
  {

  }

  public static native Object getValue(String key) throws TableKeyNotDefinedException;
  public static boolean getBoolean(String key) throws TableKeyNotDefinedException
  {
    return false;
  }
  public static double getDouble(String key) throws TableKeyNotDefinedException
  {
    return 0;
  }
  public static String getString(String key) throws TableKeyNotDefinedException
  {
    return "";
  }
  public static native byte[] getRaw(String key) throws TableKeyNotDefinedException;
  public static native boolean[] getBooleanArray(String key) throws TableKeyNotDefinedException;
  public static native double[] getDoubleArray(String key) throws TableKeyNotDefinedException;
  public static native String[] getStringArray(String key) throws TableKeyNotDefinedException;

  public static native Object getValue(String key, Object defaultValue);
  public static boolean getBoolean(String key, boolean defaultValue)
  {
    return false;
  }
  public static double getDouble(String key, double defaultValue)
  {
    return 0;
  }
  public static String getString(String key, String defaultValue)
  {
    return "";
  }
  public static native byte[] getRaw(String key, byte[] defaultValue);
  public static native boolean[] getBooleanArray(String key, boolean[] defaultValue);
  public static native double[] getDoubleArray(String key, double[] defaultValue);
  public static native String[] getStringArray(String key, String[] defaultValue);

  public static void setEntryFlags(String key, int flags)
  {

  }
  public static int getEntryFlags(String key)
  {
    return 0;
  }

  public static void deleteEntry(String key)
  {

  }
  public static void deleteAllEntries()
  {

  }

  public static native EntryInfo[] getEntries(String prefix, int types);

  public static void flush()
  {

  }

  public interface EntryListenerFunction {
    void apply(int uid, String key, Object value, int flags);
  }
  public static int addEntryListener(String prefix, EntryListenerFunction listener, int flags)
  {
    return 0;
  }
  public static void removeEntryListener(int entryListenerUid)
  {

  }

  public interface ConnectionListenerFunction {
    void apply(int uid, boolean connected, ConnectionInfo conn);
  }
  public static int addConnectionListener(ConnectionListenerFunction listener, boolean immediateNotify)
  {
    return 0;
  }
  public static void removeConnectionListener(int connListenerUid)
  {

  }

  // public static native void createRpc(String key, byte[] def, IRpc rpc);
  // public static native void createRpc(String key, ByteBuffer def, int def_len, IRpc rpc);
  public static native byte[] getRpc(String key) throws TableKeyNotDefinedException;
  public static native byte[] getRpc(String key, byte[] defaultValue);
  public static int callRpc(String key, byte[] params)
  {
    return 0;
  }
  public static int callRpc(String key, ByteBuffer params, int params_len)
  {
    return 0;
  }
  // public static native byte[] getRpcResultBlocking(int callUid);
  // public static native byte[] getRpcResultNonblocking(int callUid) throws RpcNoResponseException;

  public static void setNetworkIdentity(String name)
  {

  }
  public static void startServer(String persistFilename, String listenAddress, int port)
  {

  }
  public static void stopServer()
  {

  }
  public static void startClient(String serverName, int port)
  {

  }
  public static void stopClient()
  {

  }
  public static void setUpdateRate(double interval)
  {

  }

  public static native ConnectionInfo[] getConnections();

  public static void savePersistent(String filename) throws PersistentException
  {

  }
  public static native String[] loadPersistent(String filename) throws PersistentException;  // returns warnings

  public static long now()
  {
    return 0;
  }

  public interface LoggerFunction {
    void apply(int level, String file, int line, String msg);
  }
  public static void setLogger(LoggerFunction func, int minLevel)
  {

  }
}
