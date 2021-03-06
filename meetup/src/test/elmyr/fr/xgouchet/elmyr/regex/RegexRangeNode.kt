package fr.xgouchet.elmyr.regex

/**
 * Represents a quantifier range
 *
 * @author Xavier F. Gouchet
 */
class RegexRangeNode(parent: RegexParentNode) : RegexParentNode(parent) {

    private var readingFrom = true

    private var from = 0
    private var to = 0

    override fun handle(c: Char): Boolean {
        if (c == '}') return false

        if (readingFrom) {
            handleFrom(c)
        } else {
            handleTo(c)
        }

        return true
    }

    private fun handleFrom(c: Char) {
        val digit: Int
        when (c) {
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> digit = (c - '1') + 1
            ',' -> {
                readingFrom = false
                return
            }
            '0' -> digit = 0
            else -> throw IllegalStateException("Unexpected character in range : ‘$c’")
        }

        from = (from * BASE_10) + digit
    }

    private fun handleTo(c: Char) {
        val digit = when (c) {
            '1', '2', '3', '4', '5', '6', '7', '8', '9' -> (c - '1') + 1
            '0' -> 0
            else -> throw IllegalStateException("Unexpected character in range : ‘$c’")
        }

        to = (to * BASE_10) + digit
    }

    override fun describe(builder: StringBuilder) {
        builder.append("{")
                .append(from)
        if (!readingFrom)
            builder.append(",")
        if (to != 0)
            builder.append(to)
        builder.append("}")
    }

    fun toQuantifier(): Quantifier {
        return if (readingFrom) {
            QuantifierN(from)
        } else if (to == 0) {
            QuantifierFromN(from)
        } else {
            QuantifierFromNToM(from, to)
        }
    }

    companion object {
        const val BASE_10 = 10
    }
}
