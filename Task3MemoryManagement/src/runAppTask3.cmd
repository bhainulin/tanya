set workspace=D:/eclipseLUNA/workspace/Task3MemoryManagement
set CP_ARGS_1=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t1/MyMemoryEater
set CP_ARGS_2=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t2/JavaApp
set CP_ARGS_4_1=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t4/OutOfMemoryHeapApp
set CP_ARGS_4_2=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t4/OutOfMemoryPermGenApp
set CP_ARGS_6_1=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t6/OutOfMemoryHeapApp6
set CP_ARGS_6_2=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t6/StackOverflowErrorApp6
set CP_ARGS_7=-classpath %workspace%/lib/log4j-1.2.17.jar;%workspace%/bin com/epam/jmp/mm/t7/JavaApp7
set HEAP_DAMP=-XX:+HeapDumpOnOutOfMemoryError


@rem set MEM_ARGS_1= -Xss2M -Xms1024M -Xmx1024M -XX:SurvivorRatio=5 -XX:NewRatio=2
@rem set MEM_ARGS_1= -Xss2M -Xms100M -Xmx100M -XX:SurvivorRatio=5 -XX:NewRatio=2
set MEM_ARGS_1= -Xss2M -Xmx46M -XX:SurvivorRatio=5 -XX:NewRatio=2
@rem NewRatio = 2 (by default) = old/new if old = 256 than heap=old+new=old+0.5old=1.5old=1.5*256=384
set MEM_ARGS_2= -Xss1M -Xms384M -Xmx384M -XX:SurvivorRatio=1 -XX:NewRatio=2
set MEM_ARGS_4_1= -XX:MaxPermSize=1m -Xmx10m

@rem if -Xmx10m it will be java.lang.OutOfMemoryError: GC overhead limit exceeded
set MEM_ARGS_4_2= -XX:MaxPermSize=1m -Xmx100m

java %HEAP_DAMP% %MEM_ARGS_1% %CP_ARGS_1%

@rem java %MEM_ARGS_2% %CP_ARGS_2%

@rem java %HEAP_DAMP% %MEM_ARGS_4_1% %CP_ARGS_4_1%
@rem java %HEAP_DAMP% %MEM_ARGS_4_2% %CP_ARGS_4_2%


@rem java %MEM_ARGS_4_1% %CP_ARGS_6_1%
@rem java %MEM_ARGS_4_1% %CP_ARGS_6_2%

@rem set MEM_ARGS_7= -Xmx200m
@rem java %MEM_ARGS_4_1% %CP_ARGS_7%


