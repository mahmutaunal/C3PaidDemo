############################################
# Baseline / Safety
############################################

# Keep line numbers for better crash reports (optional but recommended)
-keepattributes SourceFile,LineNumberTable

# Keep annotations/signatures used by frameworks/reflection
-keepattributes *Annotation*,Signature,EnclosingMethod,InnerClasses

# Keep parameter names (useful for DI/reflection/logging; optional)
-keepparameternames

# Do not warn on missing optional classes (safer for consumer libs)
-dontwarn javax.annotation.**
-dontwarn org.jetbrains.annotations.**
-dontwarn kotlin.**

############################################
# Kotlin / Coroutines / Flow
############################################
# Keep metadata for Kotlin reflection/serialization edge cases
-keep class kotlin.Metadata { *; }

# Coroutines internal warnings (generally safe)
-dontwarn kotlinx.coroutines.**

############################################
# Jetpack Compose
############################################
# Compose typically works fine without keeps. These are safe guards.
-dontwarn androidx.compose.**
-dontwarn kotlin.time.**

############################################
# AndroidX Lifecycle / ViewModel
############################################
-dontwarn androidx.lifecycle.**

############################################
# Navigation (Compose Navigation)
############################################
-dontwarn androidx.navigation.**

############################################
# Material / Material3
############################################
-dontwarn androidx.compose.material.**
-dontwarn androidx.compose.material3.**

############################################
# Kotlinx Serialization (if used)
############################################
-dontwarn kotlinx.serialization.**