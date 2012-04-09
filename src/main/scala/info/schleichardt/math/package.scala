package info.schleichardt

import info.schleichardt.math.Angle

package object math {

implicit def angleToDouble(angle: Angle) = angle.radiant

}

