-keepattributes Signature
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# Giphy SDK
-keep class com.giphy.sdk.** { *; }
-keep interface com.giphy.sdk.** { *; }
-dontwarn com.giphy.sdk.**

# kotlinx.parcelize
-keep class kotlinx.parcelize.Parcelize
-keep,allowobfuscation @kotlinx.parcelize.Parcelize class *
-keepclassmembers class * {
    @kotlin.jvm.JvmField <fields>;
}

# kotlinx.serialization
-keepnames class * implements kotlinx.serialization.KSerializer
-keepclassmembers class * {
    @kotlinx.serialization.SerialName *;
    @kotlinx.serialization.Transient *;
    @kotlinx.serialization.Optional *;
    @kotlinx.serialization.Serializable *;
}
-keepclassmembers class **$$serializer {
    public static final ** INSTANCE;
    public final kotlinx.serialization.KSerializer[] childSerializers();
    public final kotlinx.serialization.KSerializer[] typeParametersSerializers();
    public final kotlinx.serialization.descriptors.SerialDescriptor getDescriptor();
    public final void serialize(kotlinx.serialization.encoding.Encoder, **);
    public final ** deserialize(kotlinx.serialization.encoding.Decoder);
    public final synthetic java.lang.Object deserialize(kotlinx.serialization.encoding.Decoder);
    public final synthetic void serialize(kotlinx.serialization.encoding.Encoder, java.lang.Object);
    private <init>();
}
