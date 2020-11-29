package com.publicissapient.publicissapienttest

import com.publicissapient.publicissapienttest.fragments.BasketFragmentTest
import com.publicissapient.publicissapienttest.fragments.DetailsFragmentTest
import com.publicissapient.publicissapienttest.navigation.NavigationTest
import org.junit.runner.RunWith
import org.junit.runners.Suite


@RunWith(Suite::class)
@Suite.SuiteClasses(
    NavigationTest::class,
    DetailsFragmentTest::class,
    BasketFragmentTest::class
)
class FragmentsTestSuite