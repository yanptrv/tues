# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /home/dex/.local/share/JetBrains/Toolbox/apps/CLion/ch-0/203.7717.62/bin/cmake/linux/bin/cmake

# The command to remove a file.
RM = /home/dex/.local/share/JetBrains/Toolbox/apps/CLion/ch-0/203.7717.62/bin/cmake/linux/bin/cmake -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/perumtations_combinations.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/perumtations_combinations.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/perumtations_combinations.dir/flags.make

CMakeFiles/perumtations_combinations.dir/main.cpp.o: CMakeFiles/perumtations_combinations.dir/flags.make
CMakeFiles/perumtations_combinations.dir/main.cpp.o: ../main.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/perumtations_combinations.dir/main.cpp.o"
	/usr/bin/c++  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles/perumtations_combinations.dir/main.cpp.o -c /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/main.cpp

CMakeFiles/perumtations_combinations.dir/main.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/perumtations_combinations.dir/main.cpp.i"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/main.cpp > CMakeFiles/perumtations_combinations.dir/main.cpp.i

CMakeFiles/perumtations_combinations.dir/main.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/perumtations_combinations.dir/main.cpp.s"
	/usr/bin/c++ $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/main.cpp -o CMakeFiles/perumtations_combinations.dir/main.cpp.s

# Object files for target perumtations_combinations
perumtations_combinations_OBJECTS = \
"CMakeFiles/perumtations_combinations.dir/main.cpp.o"

# External object files for target perumtations_combinations
perumtations_combinations_EXTERNAL_OBJECTS =

perumtations_combinations: CMakeFiles/perumtations_combinations.dir/main.cpp.o
perumtations_combinations: CMakeFiles/perumtations_combinations.dir/build.make
perumtations_combinations: CMakeFiles/perumtations_combinations.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking CXX executable perumtations_combinations"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/perumtations_combinations.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/perumtations_combinations.dir/build: perumtations_combinations

.PHONY : CMakeFiles/perumtations_combinations.dir/build

CMakeFiles/perumtations_combinations.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/perumtations_combinations.dir/cmake_clean.cmake
.PHONY : CMakeFiles/perumtations_combinations.dir/clean

CMakeFiles/perumtations_combinations.dir/depend:
	cd /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug /home/dex/Programming/tues/2020-2021/second-term/mbip/perumtations-combinations/cmake-build-debug/CMakeFiles/perumtations_combinations.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/perumtations_combinations.dir/depend

