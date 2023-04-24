package org.minutodedios.roperos.services.authentication.mock

import org.minutodedios.roperos.services.authentication.IAuthUser

class MockAuthUser(
    override val email: String
) : IAuthUser