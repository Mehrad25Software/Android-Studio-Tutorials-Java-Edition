@RunWith(AndroidJUnit4.class)
public class BottomSheetMenuFragmentTest {

    @Rule
    public FragmentScenarioRule<BottomSheetMenuFragment> fragmentRule =
            new FragmentScenarioRule<>(BottomSheetMenuFragment.class);

    @Test
    public void testMenuSettingsLaunchesSettingsActivity() {
        FragmentScenario<BottomSheetMenuFragment> scenario = fragmentRule.getScenario();
        scenario.onFragment(fragment -> {
            View settingsButton = fragment.requireView().findViewById(R.id.menuSettings);
            assertNotNull(settingsButton);
            settingsButton.performClick();
            Intent expectedIntent = new Intent(fragment.requireContext(), SettingsActivity.class);
            Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
            assertEquals(expectedIntent.getComponent(), actual.getComponent());
        });
    }

    @Test
    public void testMenuHelpFeedbackLaunchesHelpActivity() {
        FragmentScenario<BottomSheetMenuFragment> scenario = fragmentRule.getScenario();
        scenario.onFragment(fragment -> {
            View helpButton = fragment.requireView().findViewById(R.id.menuHelpFeedback);
            assertNotNull(helpButton);
            helpButton.performClick();
            Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
            assertEquals(
                "com.d4rk.androidtutorials.java.ui.screens.help.HelpActivity",
                actual.getComponent().getClassName()
            );
        });
    }

    @Test
    public void testMenuUpdatesOpensChangelog() {
        FragmentScenario<BottomSheetMenuFragment> scenario = fragmentRule.getScenario();
        scenario.onFragment(fragment -> {
            View updatesButton = fragment.requireView().findViewById(R.id.menuUpdates);
            assertNotNull(updatesButton);
            updatesButton.performClick();
            Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
            assertEquals(Intent.ACTION_VIEW, actual.getAction());
            assertTrue(actual.getData().toString().contains("CHANGELOG.md"));
        });
    }

    @Test
    public void testMenuShareIntentIsCreated() {
        FragmentScenario<BottomSheetMenuFragment> scenario = fragmentRule.getScenario();
        scenario.onFragment(fragment -> {
            View shareButton = fragment.requireView().findViewById(R.id.menuShare);
            assertNotNull(shareButton);
            shareButton.performClick();
            Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
            assertEquals(Intent.ACTION_CHOOSER, actual.getAction());
        });
    }
}
