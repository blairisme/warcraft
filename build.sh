#!/usr/bin/env sh

# Blair Butterworth (c) 2019
#
# This work is licensed under the MIT License. To view a copy of this
# license, visit
#
#      https://opensource.org/licenses/MIT
#
# This script builds and tests the system. When complete are generated
# detailing, amongst other things, the coverage achieved by testing.
#
# Author: Blair Butterworth
#

./gradlew clean build report
